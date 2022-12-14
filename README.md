# MoodMe  
## A daily journal to track how you feel

Journaling is *hard*.  Sure it sounds simple: just write about how you feel.  
But papers get scattered, you type things down and forget about them, and looking back   
through past entries becomes more exhausting than counting individual grains of sand.  

"Talked about the project with Johnson" - what does that even mean?  
Was it exciting or a snooze-fest?  You'll never be able to tell; you might as well give up.  
This journaling idea was stupid in the first place.  It's a *disaster*.

That's where MoodMe comes in.

### What does MoodMe do?

MoodMe is a Java desktop application that helps you organize your journaling.  

It allows you to:
- **Add** entries to your journal.
- **Categorize** entries based on emotion.
- **View** past entries by category.
- **Remove** past entries.

### Who will use MoodMe?

MoodMe is for anyone frustrated with the journaling process.  
From university students to busy executives, MoodMe makes mood journaling easy for everyone.  
If you want a simpler, more organized way to write about your day, then MoodMe is for you!

### Why was the creator interested in making MoodMe?

As someone who has tried (and failed) many times to start journaling, William Gao created  
MoodMe to address the issues he came across.  Like many university students,  
William struggled to find the time to journal - much less make an organized and visually  
pleasing one.  MoodMe is his answer to that problem.

### User Stories
- As a user, I want to be able to add entries to my journal
- As a user, I want to be able to categorize different entries based on mood
- As a user, I want to be able to see a summary of my past entries in a specific category
- As a user, I want to be able to remove past entries
- As a user, I want to be able to save my journal to file
- As a user, I want to be able to load my journal from file

### Instructions for Grader
- You can locate the panel that displays all entries added to the journal (Xs added to Y) by looking
at the panel titled "Entries"
- You can generate the first required event by clicking the button labelled "Add Entry" 
and following the pop-up prompts
- You can generate the second required event by dragging your mouse anywhere inside the application  
window and following the pop-up prompt
- You can locate my visual component (bar graph) by looking at the panel titled "Entry Mood Tracker"
- You can save the state of my application by clicking the button labelled "Save Journal"
- You can reload the state of my application by clicking the button labelled "Load Journal"

### Phase 4: Task 2
Wed Aug 10 17:04:59 PDT 2022  
Entry added to journal  
Wed Aug 10 17:05:03 PDT 2022  
Entry removed from journal  
Wed Aug 10 17:05:08 PDT 2022  
Entry added to journal  
Wed Aug 10 17:05:14 PDT 2022  
Entry added to journal  
Wed Aug 10 17:05:17 PDT 2022  
Entry removed from journal


### Phase 4: Task 3
- remove the need for passing Journal as a parameter to all GUI classes to ensure the  
  same one is accessed by making the journal constructor private and creating a public get instance method
- make BarGraphPanel adhere to the single responsibility principle by splitting it up into  
  separate classes for bar graph labels, bar graph bars, and bar graph titles
- reduce coupling in GUI error messages by pulling out duplicate code into a method so that  
  the formatting of the dialog boxes stays consistent
- reduce tight coupling with Journal by using the Observable design pattern and implementing necessary methods.   
Journal would be our concrete subject, and JournalApp, ActionPanel, EntryPanel, and BarGraphPanel  
would all be concrete observers.
- change implementation of ActionPanel to not need a field of MoodType by creating a separate method for each  
dialog box that returns the appropriate type, and calling that method when passing the parameters  
to create a new Entry.