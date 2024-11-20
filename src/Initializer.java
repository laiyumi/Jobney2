import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import model.application.Application;
import model.company.Company;
import model.connection.Connection;
import model.enums.APPLICATIONSTATUS;
import model.enums.INDUSTRY;
import model.job.Job;
import model.note.Note;
import model.question.Question;
import model.question.QuestionList;
import model.user.*;

public class Initializer {

	public static void initializeData(AdminUser administrator) {

		// Initialize a test user
		RegularUser testUser = new RegularUser("user@gmail.com", "1111");
//		Profile profile1 = testUser.getAssociatedProfile();
//		profile1.setUserName("HI2");
		
		administrator.getUserDirectory().addUser(testUser);
		

		// create user profile
		Profile profile1 = new Profile("Bruno");
		testUser.setAssociatedProfile(profile1);

		// create a company
		Company com1 = new Company(INDUSTRY.FINTECH, "PayPal");
		Company com2 = new Company(INDUSTRY.TECH, "Google");
		Company com3 = new Company(INDUSTRY.TECH, "Apple");
		Company com4 = new Company(INDUSTRY.EDUCATION, "Symplicity Corporation");

		Company com5 = new Company(INDUSTRY.EDUCATION, "Transit Mobility Lab");
		Company com6 = new Company(INDUSTRY.TECH, "Sedarotech");
		Company com7 = new Company(INDUSTRY.TECH, "Tencent");
		Company com8 = new Company(INDUSTRY.TECH, "Vivahr");
		Company com9 = new Company(INDUSTRY.TECH, "Github");

		administrator.getCompanyCatalog().addCompany(com1);
		administrator.getCompanyCatalog().addCompany(com2);
		administrator.getCompanyCatalog().addCompany(com3);
		administrator.getCompanyCatalog().addCompany(com4);
		administrator.getCompanyCatalog().addCompany(com5);
		administrator.getCompanyCatalog().addCompany(com6);
		administrator.getCompanyCatalog().addCompany(com7);
		administrator.getCompanyCatalog().addCompany(com8);
		administrator.getCompanyCatalog().addCompany(com9);

		// create a job under the company
		Job job1 = new Job(com1, "Software Engineer, Recent Grad",
				"https://paypal.eightfold.ai/careers?Codes=W-LINKEDIN&domain=paypal.com&query=R0119664&sort_by=relevance");
		Job job2 = new Job(com2, "Software Engineer, Early Career, Cloud AI",
				"https://www.google.com/about/careers/applications/jobs/results/129844791113327302-software-engineer-early-career-cloud-ai?src=Online%2FLinkedIn%2Flinkedin_us&utm_source=linkedin&utm_medium=jobposting&utm_campaign=contract&target_level=EARLY&target_level=INTERN_AND_APPRENTICE");
		Job job3 = new Job(com3, "Software Engineering Internships",
				"https://jobs.apple.com/en-us/details/200554359/software-engineering-internships?team=STDNT");
		Job job4 = new Job(com4, "Software Engineer, Front-End",
				"https://builtin.com/job/software-engineer-front-end/3457517");
		
		// Create additional jobs and applications for the first company
		Job job5 = new Job(com1, "Software Engineer - AI/ML - Recent Graduate",
				"https://paypal.eightfold.ai/careers?pid=274904025847&location=United%20States&recommended=1");
		Job job6 = new Job(com1, "Software Engineer Mobile - Recent Graduate",
				"https://paypal.eightfold.ai/careers?pid=274903730613&location=United%20States&recommended=1");

		Job job7 = new Job(com5, "Public Transit Visualization Developer",
				"https://undergraduate.northeastern.edu/research/opportunities/public-transit-visualization-developer/");
		Job job8 = new Job(com6, "Junior Software Engineer",
				"https://www.sedaro.com/careers");
		Job job9 = new Job(com9, "Software Engineer Intern",
				"https://githubinc.jibeapply.com/jobs/2787?iis=Job+Board&iisn=LinkedIn&lever-source=LinkedinPosting&lang=en-us");
		Job job10 = new Job(com7, "Software Engineer Intern",
				"https://tencent.wd1.myworkdayjobs.com/en-US/Tencent_Careers/job/Software-Engineer-Intern_R102779-1?source=10100001");
		Job job11 = new Job(com8, "Software Engineering Intern",
				"https://jobs.vivahr.com/7639-altitude-ai/38522-software-engineering-intern");

		// create an application using the job
		Application app1 = new Application(job1, testUser);
		Application app2 = new Application(job2, testUser);
		Application app3 = new Application(job3, testUser);
		Application app4 = new Application(job4, testUser);
		Application app5 = new Application(job5, testUser);
		Application app6 = new Application(job6, testUser);
		Application app7 = new Application(job7, testUser);
		Application app8 = new Application(job8, testUser);
		Application app9 = new Application(job9, testUser);
		Application app10 = new Application(job10, testUser);
		Application app11 = new Application(job11, testUser);

		// set up the created date for testing
		LocalDate localDate3 = LocalDate.of(2024, 10, 15);
		Date date3 = Date.from(localDate3.atStartOfDay(ZoneId.systemDefault()).toInstant());
		app3.setDateAdded(date3);

		LocalDate localDate4 = LocalDate.of(2024, 10, 16);
		Date date4 = Date.from(localDate4.atStartOfDay(ZoneId.systemDefault()).toInstant());
		app4.setDateAdded(date4);

		LocalDate localDate7 = LocalDate.of(2024, 11, 18);
		Date date7 = Date.from(localDate7.atStartOfDay(ZoneId.systemDefault()).toInstant());
		app7.setDateAdded(date7);

		LocalDate localDate8 = LocalDate.of(2024, 10, 25);
		Date date8 = Date.from(localDate8.atStartOfDay(ZoneId.systemDefault()).toInstant());
		app8.setDateAdded(date8);

		LocalDate localDate9 = LocalDate.of(2024, 11, 10);
		Date date9 = Date.from(localDate9.atStartOfDay(ZoneId.systemDefault()).toInstant());
		app9.setDateAdded(date9);

		LocalDate localDate10 = LocalDate.of(2024, 10, 18);
		Date date10 = Date.from(localDate10.atStartOfDay(ZoneId.systemDefault()).toInstant());
		app10.setDateAdded(date10);

		LocalDate localDate11 = LocalDate.of(2024, 11, 13);
		Date date11 = Date.from(localDate11.atStartOfDay(ZoneId.systemDefault()).toInstant());
		app11.setDateAdded(date11);

		// simulate application status change
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

		LocalDate localDate1 = LocalDate.of(2024, 11, 9);
		Date date1 = Date.from(localDate1.atStartOfDay(ZoneId.systemDefault()).toInstant());
		app1.setDateAdded(date1);
		String strDate1 = formatter.format(date1);
		app1.setDateApplied(strDate1);
		app1.setStatus(APPLICATIONSTATUS.APPLIED);

		LocalDate localDate2 = LocalDate.of(2024, 10, 11);
		Date date2 = Date.from(localDate2.atStartOfDay(ZoneId.systemDefault()).toInstant());
		app2.setDateAdded(date2);
		String strDate2 = formatter.format(date2);
		app2.setDateApplied(strDate2);
		app2.setStatus(APPLICATIONSTATUS.APPLIED);

		app3.setStatus(APPLICATIONSTATUS.TOAPPLY);
		app4.setStatus(APPLICATIONSTATUS.TOAPPLY);
		app7.setStatus(APPLICATIONSTATUS.TOAPPLY);
		app9.setStatus(APPLICATIONSTATUS.TOAPPLY);
		app10.setStatus(APPLICATIONSTATUS.TOAPPLY);
		app11.setStatus(APPLICATIONSTATUS.TOAPPLY);

		LocalDate localDate5 = LocalDate.of(2024, 11, 2);
		Date date5 = Date.from(localDate5.atStartOfDay(ZoneId.systemDefault()).toInstant());
		app5.setDateAdded(date5);
		String strDate5 = formatter.format(date5);
		app5.setDateApplied(strDate5);
		app5.setStatus(APPLICATIONSTATUS.APPLIED);
		app5.setStatus(APPLICATIONSTATUS.INTERVIEW);
		app5.setStatus(APPLICATIONSTATUS.GETOFFER);

		LocalDate localDate6 = LocalDate.of(2024, 11, 10);
		Date date6 = Date.from(localDate6.atStartOfDay(ZoneId.systemDefault()).toInstant());
		app6.setDateAdded(date6);
		String strDate6 = formatter.format(date6);
		app6.setDateApplied(strDate5);
		app6.setStatus(APPLICATIONSTATUS.APPLIED);
		app6.setStatus(APPLICATIONSTATUS.INTERVIEW);
		app6.setStatus(APPLICATIONSTATUS.REJECTED);

		LocalDate localDate8_applied = LocalDate.of(2024, 11, 1);
		Date date8_applied = Date.from(localDate8_applied.atStartOfDay(ZoneId.systemDefault()).toInstant());
		app8.setDateAdded(date8_applied);
		String strDate8 = formatter.format(date8_applied);
		app8.setDateApplied(strDate8);
		app8.setStatus(APPLICATIONSTATUS.APPLIED);
		app8.setStatus(APPLICATIONSTATUS.INTERVIEW);

		// create a linkedin connection
		Connection c = new Connection("Yu Lai", "https://www.linkedin.com/in/yu-lai-3588b2210/");

		app1.getConnectionList().addConnection(c);

		// create notes under one application
		Note note1 = new Note("company info",
				"PayPal is a global online payment system that serves as an electronic alternative to traditional paper methods such as checks, money orders, and cash transactions. Founded in 1998 in the United States and initially a subsidiary of eBay, it was spun off into its own company in 2015. Today, PayPal operates in more than 200 countries, allowing customers to send, receive, and hold funds in 25 currencies worldwide. The company's payment solution simplifies online transactions between businesses and consumers, providing secure and convenient digital payment options. PayPal continuously innovates its technology platform to reduce fraud risks and make online shopping easier, safer, and more user-friendly");
		Note note2 = new Note("job keywords", "detail oriented, React, Robotics experience is a plus");
		Note note3 = new Note("insights",
				"aligsn with my interest, but lack of some skills that mentioned in the job descriptioin.");
		;

		app1.getNotesList().addNote(note1);
		app1.getNotesList().addNote(note2);
		app1.getNotesList().addNote(note3);

		// add application to the user
		testUser.getApplicationList().addApplication(app1);
		testUser.getApplicationList().addApplication(app2);
		testUser.getApplicationList().addApplication(app3);
		testUser.getApplicationList().addApplication(app4);
		testUser.getApplicationList().addApplication(app5);
		testUser.getApplicationList().addApplication(app6);
		testUser.getApplicationList().addApplication(app7);
		testUser.getApplicationList().addApplication(app8);
		testUser.getApplicationList().addApplication(app9);
		testUser.getApplicationList().addApplication(app10);
		testUser.getApplicationList().addApplication(app11);

		testUser.getCompanyList().addCompany(com1);
		testUser.getCompanyList().addCompany(com2);
		testUser.getCompanyList().addCompany(com3);
		testUser.getCompanyList().addCompany(com4);
		testUser.getCompanyList().addCompany(com5);
		testUser.getCompanyList().addCompany(com6);
		testUser.getCompanyList().addCompany(com7);
		testUser.getCompanyList().addCompany(com8);
		testUser.getCompanyList().addCompany(com9);

		// add application to company
		com1.addApplication(app1);
		com1.addApplication(app5);
		com1.addApplication(app6);

		com2.addApplication(app2);
		com3.addApplication(app3);
		com4.addApplication(app4);

		com5.addApplication(app7);
		com6.addApplication(app8);
		com7.addApplication(app10);
		com8.addApplication(app11);
		com9.addApplication(app9);

		// Create some questions
		Question question1 = new Question("What are the job responsibilities?");
		question1.addAnswer("Design, develop, and test software");
		question1.addAnswer("Collaborate with cross-functional teams");
		question1.addFrequency();

		Question question2 = new Question("What are the required qualifications?");
		question2.addAnswer("Bachelor's degree in Computer Science or related field");
		question2.addAnswer("Experience with Java and Python");
		question2.addFrequency();

		Question question3 = new Question("What is the application process?");
		question3.addAnswer("Submit resume and cover letter");
		question3.addAnswer("Complete online coding test");
		question3.addFrequency();

		QuestionList ql1 = app1.getQuestionList();
		ql1.addQuestion(question1);
		ql1.addQuestion(question2);
		ql1.addQuestion(question3);

		// for testing
		System.out.print("---> user directory: \n" + administrator.getUserDirectory());
		System.out.print("---> compay catalog: \n" + administrator.getCompanyCatalog());

	}
}
