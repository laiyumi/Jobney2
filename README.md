# Jobney: A Centralized Tracking System for Job Application Management

![](/src/images/demo.gif)

## Project Overview

Built by job seekers, for job seekers, Jobney is a job application management system designed to simplifies the process of tracking every stage of your job applications in an intuitive way, serving as a central hub that organizes all aspects of your job search in one place.

With Jobney, you can easily track your applications, view your achievements and statistics, manage different versions of your resume and cover letters, set reminders, and organize your notes. The system also provides analysis to help you identify your job preferences and actions.

<img width="1196" alt="overview1" src="https://github.com/user-attachments/assets/96123e1b-8f6f-43c7-9573-b8f1f091f38e">

## Features

- **Application Mangement**: Tracks job applications in a local, centralized platforms with status updates.
- **Document Handling**: Organizes documents like resumes and cover letters for quick access and custimization.
- **Reminder System**: Receive notifications for application deadlines to prevent missed opportunities.
- **Notes & Q&A Repository**: A space for recording notes, storing job-specific questions, and saving answers for future reference.
- **LinkedIn Connection Tracker**: Track professional connections relevant.
- **Progress Tracking**: visualzie the application proecss with intuitive interface.

## Project Architecture

<img width="1335" alt="Screenshot 2024-10-31 at 19 42 16" src="https://github.com/user-attachments/assets/c8078d3b-5cc4-4c05-ae6c-ad95d1de729c">

## How to Run

### Prerequisites

- [JDK](https://www.oracle.com/java/technologies/downloads/): preferably JDK 18 or later.
- [JavaFX SDK](https://gluonhq.com/products/javafx/):
- Integrated Development Environment (IDE): preferably Eclipse.

### Setup Instructions

- Clone the Project Repository:
- Set Up JavaFX Libraries in Eclipse：
  - Open Eclipse and navigate to your project.
  - Right-click on the project in the Project Explorer, select **Properties**, then go to **Java Build Path**.
  - Under the **Libraries** tab,
    - click **Modulepath**,
    - select **Add Library ...** button,
    - choose **User Library**,
    - choose **JavaFX -> Finish -> Apply -> Apply and Close**
    - At this moment, all errors should be gone.
- Set JavaFX VM Arguments::
  - Right click **Main.java**, go to **Run As > Run Configurations**.
  - Select project’s main class under Java Application, then go to the **(x) = Arguments** tab.
  - In the **VM Arguments** field, add the following line:
    ```
    --add-modules javafx.controls,javafx.fxml
    ```
  - Unselect all the options under the **VM Arguments** field.
- Apply and Run.

## Usage

- Please reference the [Instructions.txt](Instructions.txt).
- Please check the demo video: [Demo](https://youtu.be/bKcWWTSydwU).

## Addtional Resources

#### Project Report: [Link](https://docs.google.com/document/d/1QpImLz9J1yayu8i30f3WyWGqRTSubBn4/edit?usp=sharing&ouid=114284426177446314643&rtpof=true&sd=true)

#### Slides: [Link](https://docs.google.com/presentation/d/1ZT6r_UiiytQRLCguPZym3zsI8ZSID15GqRnwdSC4kSQ/edit?usp=sharing)

#### UML Class Diagram: [Link](https://lucid.app/lucidchart/c5baed29-927d-44f6-a4ea-dda95cc16062/edit?invitationId=inv_2e5fe6aa-bac2-4c4a-b333-0c6fc8086428&page=0_0#)

#### Prototype: [Link](https://www.figma.com/proto/mpRm9GqzRcXTXuo8dgMkw8/Jobney?page-id=0%3A1&node-id=1-8&mode=design&t=qDoIqA679Ri2MJ9b-1)
