# 1. About rng
rng is an implementation of several well-known pseudo-random number generators which have far better characteristics than Java's built-in generator.
# 2. Features
Within rng you'll find the following generators (in alphabetical order):
* [Complementary multiply with carry (CMWC)][gen-cmwc]
* [Mersenne Twister 19937][gen-mt]
* [R250][gen-gfsr]
* [R250/521][gen-gfsr]
* [Xorshift Plus][gen-xorshiftplus]

All implemented generators share the same interface which makes them totally interchangeable - if you start using one of them but then want to switch to another, you'll have to change only one line of code.
# 3. Installation
The easiest and recommended way to install rng is via your favorite build tool (i.e. Maven or Gradle):
```
<dependency>
    <groupId>org.thejavaguy</groupId>
    <artifactId>rng</artifactId>
    <version>0.1.0</version>
</dependency>
```
```
compile group: 'org.thejavaguy', name: 'rng', version: '0.1.0'
```
If you want to build the jar files (with code, javadoc and sources) yourself, you can do that easily:
```bash
$ git clone git@github.com:TheJavaGuy/rng.git
$ cd rng/
$ mvn clean package -Prng
$ ls -alF target | grep rng
```
# 4. Usage examples
Using any of the generators is super easy. You just need to create an object and then request random number in the range you need.
## 4.1 Instantiating a generator
```java
PRNG.Smart generator = new MersenneTwister.Smart(new MersenneTwister());
```
In a similar way you would instantiate an object for some other generator, e.g.
```java
PRNG.Smart generator = new XorshiftPlus.Smart(new XorshiftPlus());
```
## 4.2 Generating an integer in given range
```java
IntRange range = new IntRange(1, 6);
int roll = generator.nextInt(range);
```
```IntRange``` class is constant (immutable) so you can share it's instances freely.
## 4.3 Generating a double in range [0,1)
```java
double number = generator.nextDouble();
```
## 4.4 Generating various primitive values
It's super easy to generate ```boolean```, ```byte```, ```short``` and ```char``` values too:
```java
boolean coinToss = generator.nextBoolean();
byte colorIndex = generator.nextByte();
short tetrisScore = generator.nextShort();
char letter = generator.nextChar();
```

# 5. How to contribute
If you spot an error or you think you can add some feature to rng just fork the project and make a pull request.
# 6. License
rng is licensed under [GPLv3][gpl] license.
# 7. Credits for previous work
TBD
# 8. How to contact author
If you have a question or issue with rng itself please use [Issues][rngissues] link. If you want to talk about anything else, I'm [@\_The\_Java\_Guy\_][twitterhandle] on Twitter.

[gen-cmwc]: https://en.wikipedia.org/wiki/Multiply-with-carry#Complementary-multiply-with-carry_generators
[gen-mt]: http://www.math.sci.hiroshima-u.ac.jp/~m-mat/MT/emt.html
[gen-gfsr]: https://www.unf.edu/~cwinton/html/cop4300/s09/class.notes/VLP-RNGs.pdf
[gen-xorshiftplus]: https://en.wikipedia.org/wiki/Xorshift#xorshift.2B
[gpl]: https://www.gnu.org/licenses/gpl-3.0.html
[rngissues]: https://github.com/TheJavaGuy/rng/issues
[twitterhandle]: https://twitter.com/_The_Java_Guy_