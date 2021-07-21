## CST438 Software Engineering California State University Monterey Bay
## Grade book service project
### Instructors can enter grades for an assignment

### REST apis  used by front end 

#### GET /gradebook
- returns list of assignments for an instructor that need grading
- result returned JSON for java class   AssignmentListDTO

#### GET /gradebook/{id}
- id is assignemnt_id from AssignmentListDTO 
- result is JSON for for java class GradebookDTO

#### PUT /gradebook/{id}  
- replaced scores for assignment id.
- body contains JSON for GradebookDTO

#### POST /course/{course_id}/finalgrades
- calculates final grades for course_id
- final grades are sent to registration service

### Database Tables
- Course             course_id, title, instructor's email, year, semester
- Assignment         id, name, course_id, due_date, needs_grading
- Enrollment         id, student_email, student_name, course_id
- AssignmentGrade    id, assignment_id, enrollment_id, score  

### Rest apis used by other services

#### POST /enrollment
- adds a student to a course
- body contains JSON for EnrollmentDTO

