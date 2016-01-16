# discrete-math-markdown
A specialized markdown to PDF program.

Supports almost everything in [here][https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet].

In addition, for Discrete Mathematics, the following things are included:

The following terms translate as follows
    if -> \u21D2
    iff -> \u21D4
    and -> \u2227
    or -> \u2228
    same -> \u2261
    not -> ~
when
- The line starts with @@.
- The term has a period right before it.
- The term is between @start and @end markers.

In addition, any line that starts with @truthRow is converted into a row for a truth table.  For example,

    @truthRow TFFTFTF

turns into a row for a table like this:

    |T|F|F|T|F|T|F|

## Example
    # Test Markdown
    
    ## 1.1 - Individual
    
        p .iff q .and c
    
    ## 1.2 - Line
    
        @@ not a or b if c
        @@ a same c
    
    ## 1.3 - Start / Stop
    
        @start not a or b if c
        a same c
        p iff q and c@end
        This is not processed, even if it has special terms and stuff
    
    ## 1.4 - Table
    
    |p|q|p and q|p or q|not(p and q)|not(p or q)|notp and notq|notp or notq|@@
    |-|-|-------|------|------------|-----------|-------------|------------|
    @truthRow TTTTFFFF
    @truthRow TFFTTFFT
    @truthRow FTFTTFFT
    @truthRow FFFFTTTT
