resource "aws_ecs_cluster" "cluster_1" {
  name = "cluster_1"

  setting {
    name  = "containerInsights"
    value = "enabled"
  }
}
