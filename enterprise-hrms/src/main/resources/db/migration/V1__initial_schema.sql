CREATE TABLE departments (
                             id BIGINT NOT NULL AUTO_INCREMENT,
                             description VARCHAR(255),
                             name VARCHAR(255) NOT NULL,
                             PRIMARY KEY (id),
                             CONSTRAINT uk_departments_name UNIQUE (name)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE designation (
                             id BIGINT NOT NULL AUTO_INCREMENT,
                             PRIMARY KEY (id)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE employees (
                           id BIGINT NOT NULL AUTO_INCREMENT,
                           email VARCHAR(255) NOT NULL,
                           first_name VARCHAR(255) NOT NULL,
                           joining_date DATE,
                           last_name VARCHAR(255),
                           performance_band ENUM('A', 'B', 'C', 'D', 'E'),
                           phone VARCHAR(255),
                           salary DECIMAL(38,2),
                           designation_id BIGINT,
                           department_id BIGINT,

                           PRIMARY KEY (id),

                           CONSTRAINT uk_employees_email UNIQUE (email),

                           CONSTRAINT fk_employee_designation
                               FOREIGN KEY (designation_id)
                                   REFERENCES designation(id),

                           CONSTRAINT fk_employee_department
                               FOREIGN KEY (department_id)
                                   REFERENCES departments(id)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_0900_ai_ci;