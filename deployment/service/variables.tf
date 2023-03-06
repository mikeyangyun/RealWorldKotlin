# keys to the castle variable
variable "aws_access_key" {
  type      = string
  sensitive = true
}

variable "aws_secret_key" {
  type      = string
  sensitive = true
}

variable "security_group_id" {
  type = string
}

variable "public-subnet-1-id" {
  type = string
}

variable "public-subnet-2-id" {
  type = string
}


