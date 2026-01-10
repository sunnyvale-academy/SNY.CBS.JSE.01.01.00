---
description: Create a new lab or exercise and its corresponding solution
---

### General Rules for READMEs
- **Commands**: Assume the user's terminal is already in the lab/exercise directory.
    - Use `javac *.java` for compilation.
    - Use `java ClassName` for execution (instead of using paths).

### Creating Exercises
1.  **Exercise Folder**: Create a subfolder in `exercises/<guideline-number>/<exercise-name>/`.
2.  **Exercise README**: Create a `README.md` in the exercise folder containing:
    -   The guideline reference.
    -   An explanation of the vulnerability.
    -   Instructions for the student (following the [General Rules](#general-rules-for-readmes)).
    -   Relevant Java code snippets or files.
3.  **Solution Folder**: Create a corresponding subfolder in `solutions/<guideline-number>/<exercise-name>/`.
4.  **Solution README**: Create a `README.md` in the solution folder containing the secure implementation details and code.
5.  **Solution Code**: Place the solved/secure Java files in the solution subfolder.
6.  **Root README**: Update the root `README.md` to include a link to the new exercise.

### Creating Labs
1.  **Lab Folder**: Create a subfolder in `labs/<guideline-number>/<lab-name>/`.
2.  **Lab Content**: A lab has everything in the same folder. There is no separate solution folder.
3.  **Lab README**: Create a `README.md` in the lab folder containing:
    -   The guideline reference.
    -   An explanation of the topic.
    -   Instructions following the [General Rules](#general-rules-for-readmes).
    -   PoC, experiments, or exploits as required.
4.  **Root README**: Update the root `README.md` to include a link to the new lab.
