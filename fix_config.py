import os

config_yaml = "spring:\n  cloud:\n    config:\n      server:\n        git:\n          uri: https://github.com/JEscGon/insurance-configserver\n          default-label: main\n          search-paths:\n            - config\n          clone-on-start: true\n\nserver:\n  port: 8888\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: health,info\n\nlogging:\n  level:\n    org.springframework.cloud.config.server: DEBUG\n    org.eclipse.jgit: INFO\n"

path1 = r"C:\Users\Jesus\Desktop\WORKSPACE VERIFIED\INSURANCE\insurance_configserver\src\main\resources\application.yaml"
with open(path1, 'w', encoding='utf-8') as f:
    f.write(config_yaml)
print("[OK] Config Server application.yaml")

policies_config = "server:\n  port: 8081\n\nspring:\n  main:\n    allow-bean-definition-overriding: true\n  datasource:\n    url: ${SPRING_DATASOURCE_URL:jdbc:postgresql://localhost:5433/insurance-policies}\n    username: ${SPRING_DATASOURCE_USERNAME:postgres}\n    password: ${SPRING_DATASOURCE_PASSWORD:root}\n    driver-class-name: org.postgresql.Driver\n  jpa:\n    hibernate:\n      ddl-auto: update\n    show-sql: true\n    generate-ddl: false\n  flyway:\n    enabled: true\n    locations: classpath:db/migration\n\nspringdoc:\n  api-docs:\n    path: /v3/api-docs\n  swagger-ui:\n    path: /swagger-ui.html\n"

path2 = r"C:\Users\Jesus\Desktop\WORKSPACE VERIFIED\INSURANCE\insurance_configserver\config\insurance-policies-local.yml"
with open(path2, 'w', encoding='utf-8') as f:
    f.write(policies_config)
print("[OK] insurance-policies-local.yml")

print("DONE")

