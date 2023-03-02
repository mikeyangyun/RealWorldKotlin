resource "aws_route_table" "route_table_1" {
  vpc_id = aws_vpc.demo-app.id

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.igw.id
  }

  route {
    ipv6_cidr_block = "::/0"
    gateway_id      = aws_internet_gateway.igw.id
  }

  tags = {
    Name = "route_table_1"
  }
}
