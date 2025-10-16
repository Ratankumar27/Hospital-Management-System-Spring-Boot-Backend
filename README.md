<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>

   <h1>🏥 Hospital Management System — Backend</h1>

  <p>A robust and production-grade backend system built using <strong>Spring Boot, Hibernate (JPA), and MySQL</strong>, designed to simulate real-world hospital operations — including management of patients, doctors, departments, appointments, and insurance.</p>

   <p>This project showcases advanced <strong>Spring Data JPA relationships, transactional service design, entity lifecycle management, and integration testing</strong>, making it ideal for both learning and enterprise-grade backend development.</p>

  <div class="section">
      <h2>🧭 Overview</h2>
      <p>The Hospital Management System (HMS) backend is a <strong>Spring Boot–powered microservice</strong> that models and manages core hospital data operations. It demonstrates how to design highly relational, transactional, and modular backend systems using Java and modern Spring frameworks.</p>

  <p>The system handles:</p>
  <ul>
      <li>Patient registration and record management</li>
      <li>Doctor and department assignments</li>
      <li>Appointment creation and reassignment</li>
      <li>Insurance association and disassociation</li>
      <li>Data persistence with cascading, orphan removal, and transaction safety</li>
  </ul>
  </div>

  <div class="section">
      <h2>⚙️ Tech Stack</h2>
      <table>
          <tr><th>Layer</th><th>Technologies Used</th></tr>
          <tr><td>Language</td><td>Java 21</td></tr>
          <tr><td>Framework</td><td>Spring Boot 3.5.5</td></tr>
          <tr><td>ORM</td><td>Hibernate / Spring Data JPA</td></tr>
          <tr><td>Database</td><td>MySQL 8.x</td></tr>
          <tr><td>Build Tool</td><td>Maven</td></tr>
          <tr><td>Testing</td><td>JUnit 5, Spring Boot Test</td></tr>
          <tr><td>Code Simplification</td><td>Lombok</td></tr>
      </table>
  </div>

  <div class="section">
      <h2>🏗️ Architecture</h2>
      <pre>
com.ratankumar.gmail.hospitalManagement
│
├── entity/                # Domain models (Patient, Doctor, Appointment, Department, Insurance)
├── repository/            # Data Access Layer (JpaRepository interfaces + custom JPQL)
├── service/               # Business Logic Layer (Transactional service methods)
├── HospitalManagementApplication.java  # Spring Boot main entry point
└── test/                  # JUnit 5 tests for integration and service layers
        </pre>

  <h3>🧱 Design Pattern</h3>
        <ul>
            <li><strong>Entity Layer</strong> → Defines relational models and mappings</li>
            <li><strong>Repository Layer</strong> → Handles data persistence and queries</li>
            <li><strong>Service Layer</strong> → Encapsulates transactional business logic</li>
            <li><strong>Testing Layer</strong> → Validates end-to-end operations using Spring context</li>
        </ul>
    </div>

  <div class="section">
        <h2>🧠 Core Modules and Operations</h2>

  <h3>🩺 1. Patient Management</h3>
        <ul>
            <li>Maintains patient details: name, gender, DOB, and blood group</li>
            <li>Establishes:
                <ul>
                    <li>@OneToOne → Insurance</li>
                    <li>@OneToMany → Appointments</li>
                </ul>
            </li>
            <li>Includes unique constraints for identity validation</li>
            <li>Supports cascading saves and orphan removal for lifecycle safety</li>
        </ul>

  <h3>👨‍⚕️ 2. Doctor Management</h3>
  <ul>
      <li>Stores doctor data: name, specialization, and contact info</li>
      <li>Configures:
          <ul>
              <li>@ManyToMany → Departments</li>
              <li>@OneToMany → Appointments</li>
          </ul>
      </li>
      <li>Demonstrates advanced bidirectional mapping and lazy loading</li>
  </ul>

  <h3>🏥 3. Department Management</h3>
  <ul>
      <li>Represents medical departments in the hospital</li>
      <li>Relationships:
          <ul>
              <li>@OneToOne → Head Doctor</li>
              <li>@ManyToMany → List of Doctors</li>
          </ul>
      </li>
      <li>Enables structured mapping between staff and hospital divisions</li>
  </ul>

  <h3>📅 4. Appointment Scheduling</h3>
  <ul>
      <li>Central entity linking Patient ↔ Doctor</li>
      <li>Operations include:
          <ul>
              <li>Creating new appointments</li>
              <li>Reassigning appointments to different doctors</li>
          </ul>
      </li>
      <li>Uses transactional service logic for data consistency</li>
      <li>Maintains bidirectional relationships with cascade and orphan rules</li>
  </ul>

  <h3>💳 5. Insurance Management</h3>
  <ul>
      <li>Handles patient insurance with provider and policy details</li>
      <li>Key Operations:
          <ul>
              <li>Assign insurance to patient</li>
              <li>Disassociate insurance (remove link safely)</li>
          </ul>
      </li>
      <li>Implements @OneToOne with cascading and orphan removal</li>
      <li>Demonstrates clean detachment and reassociation logic</li>
  </ul>
  </div>

  <div class="section">
      <h2>🔗 Entity Relationship Overview</h2>
      <table>
          <tr><th>Relationship</th><th>Type</th><th>Description</th></tr>
          <tr><td>Patient ↔ Appointment</td><td>One-to-Many</td><td>A patient can have multiple appointments</td></tr>
          <tr><td>Patient ↔ Insurance</td><td>One-to-One</td><td>A patient can have one insurance policy</td></tr>
          <tr><td>Doctor ↔ Appointment</td><td>One-to-Many</td><td>A doctor can handle multiple appointments</td></tr>
          <tr><td>Department ↔ Doctor</td><td>Many-to-Many</td><td>A department can have multiple doctors</td></tr>
          <tr><td>Department ↔ Head Doctor</td><td>One-to-One</td><td>Each department has one head doctor</td></tr>
      </table>

   <p>All relationships are bidirectional and maintained via <code>mappedBy</code>, <code>@JoinColumn</code>, and cascade operations ensuring referential integrity.</p>
  </div>

  <div class="section">
      <h2>🧰 Repository Layer</h2>
      <p>Each repository extends <code>JpaRepository</code> for CRUD functionality. Custom JPQL queries are added for complex joins and eager loading.</p>
      <pre>
@Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointments")
List&lt;Patient&gt; findAllPatientWithAppointments();
        </pre>
        <p>This retrieves all patients with their appointments in a single optimized query.</p>
    </div>

  <div class="section">
      <h2>⚙️ Business Logic — Service Layer</h2>
      <h3>🔹 AppointmentService</h3>
      <ul>
          <li>Creates new appointments (linking doctor and patient)</li>
          <li>Reassigns appointments to different doctors</li>
          <li>Ensures atomic transactions with <code>@Transactional</code></li>
      </ul>
      <h3>🔹 InsuranceService</h3>
      <ul>
          <li>Assigns or removes insurance from patients</li>
          <li>Maintains bidirectional mapping between patient and insurance</li>
          <li>Handles orphan removal cleanly during disassociation</li>
      </ul>
      <p>Every method is transactional — ensuring atomicity and data consistency across the system.</p>
  </div>

   <div class="section">
      <h2>🧪 Testing Suite</h2>
      <p>Comprehensive <strong>JUnit 5</strong> integration tests validate all service and repository behaviors using the full Spring context.</p>
      <ul>
          <li>✅ <strong>Appointment Tests:</strong> Create and reassign appointments, verify transactional consistency</li>
          <li>✅ <strong>Insurance Tests:</strong> Assign and disassociate insurance, validate One-to-One relationship integrity</li>
          <li>✅ <strong>Patient Tests:</strong> Fetch patients with their appointments, test JPQL queries and data relationships</li>
      </ul>
  </div>

  <div class="section">
        <ul>
            <li>Auto schema creation and data seeding</li>
            <li>SQL query logging for debugging</li>
            <li>Ready for MySQL or Docker setup</li>
        </ul>
    </div>

  <div class="section">
      <h2>🧩 Build & Run</h2>
      <ul>
          <li>▶️ <strong>Run Application:</strong> <code>mvn spring-boot:run</code></li>
          <li>🧪 <strong>Run Tests:</strong> <code>mvn test</code></li>
          <li>🏗️ <strong>Build Package:</strong> <code>mvn clean package</code></li>
      </ul>
  </div>

  <div class="section">
      <h2>🚀 Highlights</h2>
      <ul>
          <li>✅ Layered architecture with clean separation of concerns</li>
          <li>✅ Complex entity mapping with cascade, orphan removal, and bidirectional consistency</li>
          <li>✅ Fully transactional service layer ensuring data integrity</li>
          <li>✅ Extensive JUnit test coverage for all core modules</li>
          <li>✅ Built using Spring Boot 3.5.5 and Java 21</li>
          <li>✅ Ready for REST API or frontend integration (React / Angular)</li>
          <li>✅ Demonstrates industry-grade backend engineering practices</li> 
      </ul>
  </div>
<div>
 <h3>Hospital Management System Structure</h3>
hospitalManagement/<br>
│<br>
├── src/<br>
│   ├── main/ <br>
│   │   ├── java/com/ratankumar/gmail/hospitalManagement/<br>
│   │   │   ├── entity/        <span class="comment"># Patient, Doctor, Department, Appointment, Insurance</span><br>
│   │   │   ├── repository/    <span class="comment"># JpaRepository interfaces</span><br>
│   │   │   ├── service/       <span class="comment"># AppointmentService, InsuranceService</span><br>
│   │   │   └── HospitalManagementApplication.java<br>
│   │   └── resources/<br>
│   │       ├── application.properties<br>
│   │       └── data.sql<br>
│   └── test/<br>
│       └── java/com/ratankumar/gmail/hospitalManagement/<br>
│           ├── PatientsTests.java<br>
│           ├── AppointmentTest.java<br>
│           └── InsuranceTests.java<br>
├── pom.xml<br>
└── README.html<br>
</div>
</body>
</html>
