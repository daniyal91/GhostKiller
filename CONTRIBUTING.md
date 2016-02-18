# Contributing Guidelines

Some basic conventions for contributing to this project.

## Coding style

The style adopted for the project is based on Google's style for Java.
However, the style used in this project differs from Google's style in
the following ways:

  * The maximum line width is 120 instead of 80;
  * The indentation should use only spaces, and be composed of 4 instead of 2 spaces;
  * Variable name can be composed of less than 3 characters, as long as their scope is limited (index of a for loop).

Otherwise, standard style guidelines from Google's style are applied, including:

  * Variable names should be in camelCase, class names in UpperCamelCase, and constants in UPPER_CASE_WITH_UNDERSCORES;
  * Only one class per file;
  * Spaces around operators, before curly braces;
  * No empty lines with spaces;

The complete list of style verifications is available in the *checkstyle-java-google-style.xml* and
*eclipse-java-google-style.xml* files. These files are used for auto-formatting of the code according
to the coding conventions stated above.

## Contributions

Contributions to the project are made using GitHub's pull requests. Team members develop their features
on feature branches. When a feature is ready, a pull request is created so that other team members
can review the changes of add code to the feature. Code review is mandatory for new features.
Every pull request should be reviewed by at least one other team member.

Collaborators are also required to run the test suite before merging a pull request into the master
branch, to make sure that the new feature does not break the existing codebase.
