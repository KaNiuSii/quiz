# Quiz
## Quiz System Project Description

This is a comprehensive online quiz management system designed for educational environments. The platform allows for three user roles (admin, teacher, student) and supports various question types while tracking student submissions and performance.

## Core features

**User Managment**    
- Three distinct user roles: admin, teacher and student
- Secure authentication with email/password
- User profile management

**Class Management**
- Teachers can create classes with unique invitation codes
- Students can join classes using these codes
- Classes serve as containers for quizzes and student groups

**Quiz Creation and Management**
- Teachers can create quizzes within their classes
- Quizzes have defined start/end times and duration limits
- Support for different question types:
    - Single-choice questions
    - Multiple-choice questions
    - Open-ended questions
- Point-based scoring system for questions

**Quiz Taking and Submission**
- Students can submit answers within the designated timeframe
- System records submission time and answers
- Support for selecting multiple options for multiple-choice questions
- Text input for open-ended questions

**Grading and Feedback**
- Automatic grading for single/multiple choice questions
- Manual grading interface for teachers to evaluate open-ended questions
- Teachers can provide comments on student answers
- Point tracking for student performance

## Running the Application

### Using Docker
1. Clone the repository
``` bash
git clone https://github.com/KaNiuSii/quiz
```

2. Go to main directory
``` bash
cd quiz
```

3. Build and start containers
``` bash
docker-compose down --volumes --remove-orphans
docker volume prune -f
docker-compose up -d
```

4. Run the Spring Application

The API will be available at the http://localhost:8080
