resource "aws_security_group" "security-group" {
  name        = "security-group"
  description = "Security group of subnet"
  vpc_id      = aws_vpc.demo-app.id

  ingress {
    description = "Http"
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["202.66.38.130/32"]
  }


  egress {
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }

  tags = {
    Name = "security-group"
  }
}
