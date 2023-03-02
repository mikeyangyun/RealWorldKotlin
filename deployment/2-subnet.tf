resource "aws_subnet" "public-subnet-1" {
  vpc_id            = aws_vpc.demo-app.id
  cidr_block        = "10.0.0.0/20"
  availability_zone = "us-east-1"

  tags = {
    Name = "public-subnet-1"
  }
}

resource "aws_subnet" "public-subnet-2" {
  vpc_id            = aws_vpc.demo-app.id
  cidr_block        = "10.0.16.0/20"
  availability_zone = "ap-southeast-1b"

  tags = {
    Name = "public-subnet-2"
  }
}
