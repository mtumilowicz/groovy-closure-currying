# groovy-closure-currying
Overview of groovy closure currying.

_Reference_: https://www.jstips.co/en/javascript/curry-vs-partial-application/  
_Reference_: https://en.wikipedia.org/wiki/Currying  
_Reference_: https://en.wikipedia.org/wiki/Partial_application
_Reference_: https://en.wikipedia.org/wiki/Arity  

# preface

## currying
In mathematics and computer science, currying is the technique of 
translating the evaluation of a function that takes multiple 
arguments into evaluating a sequence of functions, each with a 
single argument. For example, a function that takes two arguments, 
one from X and one from Y, and produces outputs in Z, by currying 
is translated into a function that takes a single argument from X 
and produces as outputs functions from Y to Z. **Currying is related 
to, but not the same as, partial application.**

### example
* add: X x X -> X
```
function add(x, y) {
  return x + y;
}
```
* curried add: X -> (X -> X)
```
function curriedAdd(x) {
  return function (y) {
    return x + y;
  }
}
```
and calling:
```
add(3, 5);
curriedAdd(3)(5);
```

## partial application
In computer science, partial application (or partial function application) 
refers to the process of fixing a number of arguments to a function, 
producing another function of smaller arity (arity of a function or 
operation is the number of arguments or operands that the function takes.)

### example
* add: X x X -> X
```
function add(x, y) {
  return x + y;
}
```
* partial add: (X -> X)
```
function partialAdd_5(y) {
  return add(5, y);
}
```
and calling:
```
add(5, 3);
partialAdd_5(3);
```

## currying vs partial application
* currying
Currying takes a function

`f: X x Y -> R`

and turns it into a function

`f': X -> (Y -> R)`

Thus, if the uncurried f is invoked as

`f(3,5)`

then the curried `f’` is invoked as

`f'(3)(5)`

* partial application

Partial application takes a function

`f: X x Y -> R`

and a fixed value for the first argument to produce a new function

`f': Y -> R`

`f’` does the same as `f`, but only has to fill in the second 
parameter which is why its arity is one less than the arity of f.


# groovy currying
In Groovy, currying refers to the concept of partial application. 
It does not correspond to the real concept of currying in functional 
programming because of the different scoping rules that Groovy 
applies on closures. Currying in Groovy will let you set the 
value of one parameter of a closure, and it will return a new 
closure accepting one less argument.

It is quite useful with `.&` operator: 
https://github.com/mtumilowicz/groovy-transform-method-to-closure

We have 3 approaches to currying in groovy:
* **Left currying** - setting the left-most parameter of a closure
* **Right currying** - setting the right-most parameter of a closure
* **Index based currying** - In case a closure accepts more than 
2 parameters, it is possible to set an arbitrary parameter using `ncurry`

