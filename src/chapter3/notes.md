<h1>Notes</h1>
<p>Lambda  is a concise representation of anonymous function that can be passed around in a concise way.</p>
<ul>
<li>Anonymous - it doesn't have an explicit name</li>
<li>Function - it is not associated with a particular class. It can have list of parameters, body, a return type and list of exceptions</li>
<li>Passed around - a lambada expression can be passed as argument to a method or stored in variable</li>
<li>Concise - you don't need a boilerplate code to write a lambda</li>
</ul>
<br/>
<p>Anonymous Interface is a interface with one method exactly</p>
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