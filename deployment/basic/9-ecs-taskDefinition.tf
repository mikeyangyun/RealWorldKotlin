resource "aws_ecs_task_definition" "task_definition_demo" {
  family                   = "demo-task-definition"
  task_role_arn            = aws_iam_role.ecs_task_execution_role.arn
  execution_role_arn       = aws_iam_role.ecs_task_execution_role.arn
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  runtime_platform {
    operating_system_family = "LINUX"
    cpu_architecture        = "X86_64"
  }
  cpu                   = 2048
  memory                = 8192
  container_definitions = <<DEFINITION
  [
   {
      "name":"demoContainer",
      "image":"316682785549.dkr.ecr.us-east-1.amazonaws.com/demo-repository:myapp",
      "cpu":0,
      "memory":null,
      "essential":true,
      "logConfiguration": {
        "logDriver": "awslogs",
        "secretOptions": null,
        "options": {
          "awslogs-group": "/cluster_1/ecs_service_1",
          "awslogs-region": "us-east-1",
          "awslogs-stream-prefix": "cluster_1"
        }
      },
      "portMappings":[
         {
            "hostPort":8080,
            "protocol":"tcp",
            "containerPort":8080
         }
      ]
   }
]
  DEFINITION
}
