const num = Math.floor(Math.random() * (10 - 1) + 1); // Generating random number between 1 and 10

let totalGuesses = 0;
console.log("** I have a number in my mind would you able to guess it **\n")
console.log("Guess the number between 1 and 10 (including)\n")
while (true) {
  let userNum = prompt("Guess the number??");
  if (totalGuesses > 10) {              // Checking if the user has guessed more than 10 times
    console.log("You have guessed enough!!");
    console.log("Your final score is", (11 - totalGuesses));    // Printing the final score if the user guessed more than 10 times
    console.log("You're ROOKIE!! All the best for next time"); // Complimenting the user if he guessed more than 10 times
    break;
  }
  else {
    if (userNum == num) {   // Checking if the user guessed the correct number
      totalGuesses++;       // Incrementing the total guesses
      let TYPE = 10 - totalGuesses;     // Calculating the score on the basis of total guesses
      console.log("\nYou got it man!!");    // Printing the final score if the user guessed the correct number
      console.log("Your score is", TYPE, " out of 10"); // Printing the final score if the user guessed the correct number
      if (TYPE == 9) {
        console.log("Woah !! that was impressive ");    // complimenting the user if he guessed the correct number in first attempt
      }
      else if (TYPE >= 7 && TYPE < 9){              
        console.log("Good!! you guessed it")    // Complimenting the user if he guessed the correct number in 2-3 attempts
      }
      else if (TYPE >= 4 && TYPE < 7) {
        console.log("You took it long but guessed somehow!!")   // Complimenting the user if he guessed the correct number in 4-6 attempts
      }
      else {
        console.log("Hmm Dont worry you will be doing good next time !!!")  // Complimenting the user if he guessed the correct number in more than 6 attempts
      }
      break;
    }
    else {
      console.log("OOPS not this one try again !!!");       // If the user guessed the wrong number
      totalGuesses++;
    }
  }
}