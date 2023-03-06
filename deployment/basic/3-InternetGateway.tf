resource "aws_internet_gateway" "igw" {
  vpc_id = aws_vpc.demo-app.id
  tags = {
    Name = "igw"
  }
}
