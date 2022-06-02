<h1>Notes</h1>
<p><strong>Lambda</strong>  is a concise representation of anonymous function that can be passed around in a concise way.</p>
<ul>
<li>Anonymous - it doesn't have an explicit name</li>
<li>Function - it is not associated with a particular class. It can have list of parameters, body, a return type and list of exceptions</li>
<li>Passed around - a lambada expression can be passed as argument to a method or stored in variable</li>
<li>Concise - you don't need a boilerplate code to write a lambda</li>
</ul>
<br/>
<p><strong>Anonymous Interface</strong> is a interface with one method exactly</p>
<p>Example: <code>interface Predicate T {boolean test(T t)} </code>. Other examples of functional interfaces are Comparator, Runnable, ActionListener, Callable, PrivilegedAction
</p>

<p>Lambda expression let you provide the implementation of a functional interface and treat whole expression
as the direct instance of the functional interface.
</p>
<p>Same can be achieved with anonymous class but it is clumsier</p>
<p>
<code>Runnable r1 = () -> System.out.println("Hello World1")</code>
<br/>
<code>Runnable r2 = new Runnable(){
    public void(){
    System.out.println("Hello World1")
}
}</code>
<br/>
<code>
public static void process(Runnable r){
    r.run();
}
</code>
<br/>
<code>process(r1)</code>
<br/>
<code>process(r2)</code>
</p>
<p>
<strong>Examples of interface:</strong>
Predicate - method name(test -> boolean), Consumer - method name(accept -> void), Function (T, R) - interface defines an abstract method name( R apply (T t)) that accepts generic type T as input and returns generic type R as output
</p>
<p>Also, Java 8 allows to utilize interfaces without boxing. Example 
<code>IntPredicate evenNumbers = (int i) -> i % 2 == 0</code>(no boxing),
<code>Predicate(Integers) oddNumbers = (Integer i) -> i % 2 == 1</code>(boxing)
</p>
<p><strong>Local Variable</strong> - lambda can only use captured local variable that are final. You can't modify them later on</p>
<ul>
<li>Local variables are stored in stack and instance variables are stored in heap </li>
<li>Each thread gets its own stack. Hence, each thread will have its own copy of local variable. Workaround is to allows lambada to utilize variable from heap</li>
<li>Restriction exists because local variable live on the stack and implicitly confined to the thread they are on</li>
<li>Allowing to capture mutable local variable is thread unsafe. Instance variables are fine because they live on heap which is shared across threads</li>

</ul>