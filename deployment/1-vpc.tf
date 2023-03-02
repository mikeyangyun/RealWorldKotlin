resource "aws_vpc" "demo-app" {
  cidr_block           = "10.0.0.0/16"
  enable_dns_hostnames = true

  tags = {
    Name = "demo-app"
  }
}
