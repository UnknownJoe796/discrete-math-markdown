# discrete-math-markdown
A specialized version of Markdown to PDF program.

This program takes your homework (in a special Markdown file, decribed below) and turns it into a decent-looking PDF.

What is Markdown?  You can see a good example [here](https://en.wikipedia.org/wiki/Markdown#Example).  Basically, it makes it easy to write simple files that look pretty when you run them through a program.  This program has a couple of extensions on it to make it easy to use for our Discrete Mathematics class at Utah State University.

## Downloading and Using

Download [CurrentVersion.zip](https://github.com/UnknownJoe796/discrete-math-markdown/raw/master/CurrentVersion.zip) and extract it.
Find and open the "bin" folder.
Put the files you want to run it through in there - they can be either Markdown (.md) or plain text (.txt) files.
Run the batch file (if on Windows, don't know about other OSes).

If you want an example file to use, try [executable/test.md](executable/test.md).  It should help you a bit in understanding the format.

## What you can do

This program supports almost everything in https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet.

In addition, for Discrete Mathematics, the following things are included:

The following terms translate as follows

    if -> ⇒
    iff -> ⇔
    and -> ∧
    or -> ∨
    same -> ≡
    not -> ~
    
when
- The line contains a @@.
- The term has a period right before it.
- The term is between @start and @end markers.

In addition, any line that starts with @truthRow is converted into a row for a truth table.  For example,

    @truthRow TFFTFTF

turns into a row for a table like this:

    |T|F|F|T|F|T|F|

## Example
    # My Homework
    
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
---
### becomes:
---
<h1><a href="#test-markdown" name="test-markdown">Test Markdown</a></h1>
<h2>1.1 - Individual</h2>
<pre><code>p ⇔ q ∧ c
</code></pre>
<h2>1.2 - Line</h2>
<pre><code> ~ a ∨ b ⇒ c
 a ≡ c
</code></pre>
<h2>1.3 - Start / Stop</h2>
<pre><code> ~ a ∨ b ⇒ c
a ≡ c
p ⇔ q ∧ c
This is not processed, even if it has special terms and stuff
</code></pre>
<h2>1.4 - Table</h2>
<table>
  <thead>
    <tr>
      <th>p</th>
      <th>q</th>
      <th>p ∧ q</th>
      <th>p ∨ q</th>
      <th>~(p ∧ q)</th>
      <th>~(p ∨ q)</th>
      <th>~p ∧ ~q</th>
      <th>~p ∨ ~q</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>T</td>
      <td>T</td>
      <td>T</td>
      <td>T</td>
      <td>F</td>
      <td>F</td>
      <td>F</td>
      <td>F</td>
    </tr>
    <tr>
      <td>T</td>
      <td>F</td>
      <td>F</td>
      <td>T</td>
      <td>T</td>
      <td>F</td>
      <td>F</td>
      <td>T</td>
    </tr>
    <tr>
      <td>F</td>
      <td>T</td>
      <td>F</td>
      <td>T</td>
      <td>T</td>
      <td>F</td>
      <td>F</td>
      <td>T</td>
    </tr>
    <tr>
      <td>F</td>
      <td>F</td>
      <td>F</td>
      <td>F</td>
      <td>T</td>
      <td>T</td>
      <td>T</td>
      <td>T</td>
    </tr>
  </tbody>
</table>
