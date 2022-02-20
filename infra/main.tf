provider "aws" {
  region = var.region
}

data "aws_availability_zones" "available" {}

module "vpc" {
  source  = "terraform-aws-modules/vpc/aws"
  version = "2.77.0"

  name                 = "temporal"
  cidr                 = "10.0.0.0/16"
  azs                  = data.aws_availability_zones.available.names
  public_subnets       = ["10.0.4.0/24", "10.0.5.0/24", "10.0.6.0/24"]
  enable_dns_hostnames = true
  enable_dns_support   = true
}

resource "aws_db_subnet_group" "temporal" {
  name       = "temporal"
  subnet_ids = module.vpc.public_subnets

  tags = {
    Name = "Temporal"
  }
}

resource "aws_security_group" "rds" {
  name   = "education_rds"
  vpc_id = module.vpc.vpc_id

  ingress {
    from_port   = 5432
    to_port     = 5432
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 5432
    to_port     = 5432
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "education_rds"
  }
}

resource "aws_db_parameter_group" "temporal" {
  name   = "temporal"
  family = "postgres13"

  parameter {
    name  = "log_connections"
    value = "1"
  }
}

resource "aws_db_instance" "temporal" {
  identifier             = "temporal"
  instance_class         = "db.t3.micro"
  allocated_storage      = 10
  backup_retention_period   = 1
  apply_immediately      = true
  engine                 = "postgres"
  engine_version         = "13.1"
  username               = "edu"
  password               = var.db_password
  db_subnet_group_name   = aws_db_subnet_group.temporal.name
  vpc_security_group_ids = [aws_security_group.rds.id]
  parameter_group_name   = aws_db_parameter_group.temporal.name
  publicly_accessible    = true
  skip_final_snapshot    = true
}

resource "aws_db_instance" "temporal_replica" {
  identifier             = "temporal-replica"
  replicate_source_db    = aws_db_instance.temporal.identifier
  instance_class         = "db.t3.micro"
  apply_immediately      = true
  publicly_accessible    = true
  skip_final_snapshot    = true
  vpc_security_group_ids = [aws_security_group.rds.id]
  parameter_group_name   = aws_db_parameter_group.temporal.name
}