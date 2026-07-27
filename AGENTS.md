## Java Style

* 2-space indentation (never tabs), including continuation lines
* 150 character line limit
* LF line endings, UTF-8 charset
* K&R brace style — opening brace on same line
* Always use braces even for single-line blocks
* Method chains break after the dot. Class members: constants → fields → constructors → methods.
* No wildcard imports
* Always use explicit imports at the top of the file — never use fully-qualified names in field declarations, constructor parameters, or method signatures (e.g., use `AccountService accountService` not `com.everee.application_contracts.account.AccountService accountService`)
* Use `var` where type is obvious

**No blank lines after class/interface declarations (critical):**
All class, interface, and nested class declarations MUST be immediately followed by their first member with zero blank lines. This applies to top-level classes, nested classes, and test classes.
