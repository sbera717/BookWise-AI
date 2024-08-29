
**Have you had a breakup and are feeling sad? Or perhaps you had a stressful day and are feeling frustrated? Yes, I am talking about bibliotherapy using books. We all know that books are the best companions; when we read, it feels good and helps our minds focus on new things.**

# **AI BookWise** 
An innovative library management system with a touch of AI. This system goes beyond traditional library management by offering bibliotherapy to help users through tough times. Here are some of the key features:

# **Key-Features**

**Sentiment Analysis and Book Recommendations:**
Utilize AI-driven sentiment analysis to recommend books that uplift mood and provide relaxation based on user emotions such as sadness or stress.

**Automated Notifications:**
Send email reminders one day before due dates using a cron scheduling system, helping users manage their library loans effectively.
 
**Fast Data Retrieval:**
Utilize Redis for caching to achieve up to 60% improvement in data retrieval speed, enhancing overall system performance.

**AI Book Recommendations via Email:**
Leverage OpenAI to recommend three books via email, personalized based on the user's recent returns, fostering continuous engagement.

**CI/CD Pipeline:**
Implement a streamlined CI/CD pipeline for seamless integration and deployment of new features, ensuring efficient development processes.

#                                                                           **Project Stack**

**Spring Boot:** Main framework for the core application logic.

**FastAPI:** Used specifically for AI functionalities.

**Database:** Utilizes SQL Server for the main database. However, it's adaptable to any SQL database system.

**Caching:** Redis is employed for caching purposes, enhancing performance by up to 60%.

**Email Services:** Google SMTP server is utilized for sending email notifications.

for detail description of how fastapi works please refer to the **GitHub repository** : https://github.com/sbera717/FastApi-OpenAI.

#                                                                              **Deployment**

**Spring Boot:**
Deployment managed using Jenkins as the CI tool and AWS Lambda for Continuous Deployment (CD).

**FastAPI:**
GitHub Actions handle the entire CI/CD process for FastAPI deployment.

#                                                                           **Running the Application:**

Clone the repository into your local system.

Run **mvn clean package** (make sure that you have **Java 17** and above and **Maven 3.8** and above installed on your local system).

The JAR file will be generated in the target folder.

Run **java -jar "name-of-the-jar-file"**.

Ensure that SQL Server and Redis are installed. You can also use MySQL by modifying the details in the **application.properties** file.

**Note**: To use AI functionality, refer to the FastAPI README file. The other application should run concurrently with this one as it provides necessary data for functionality. 

![5 july drawio](https://github.com/user-attachments/assets/946989ed-b524-4968-9f9a-f3d75aa78875)

