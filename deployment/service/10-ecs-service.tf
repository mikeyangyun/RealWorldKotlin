resource "aws_ecs_service" "ecs_service_1" {
  name             = "demo-service"
  cluster          = aws_ecs_cluster.cluster_1.id
  task_definition  = aws_ecs_task_definition.task_definition_demo.id
  desired_count    = 1
  launch_type      = "FARGATE"
  platform_version = "LATEST"

  network_configuration {
    assign_public_ip = true
    security_groups  = [var.security_group_id]
    subnets          = [var.public-subnet-1-id, var.public-subnet-2-id]
  }
}
