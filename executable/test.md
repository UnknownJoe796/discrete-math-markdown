@singleMarker(@)

# Test Markdown

## 1.1 - Individual

    p @iff q @and c

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
