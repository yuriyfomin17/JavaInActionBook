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
<p><strong>Stream</strong> is an api that allows to manipulate with collection. Big advantage of streams is that they allow to process data in parallel and run code in parallel</p>
<p><strong>Stream</strong> is a sequence of elements from the source that supports data processing operations</p>
<ul>
<li><strong>Sequence of elements</strong> - stream provides and interface to sequenced set of values of a specific element type. Collections are data structures, they are about storing and accessing elements with specific time/space complexities.
Collections are about data; stream are about computations (filter, sorted, map)
</li>
<li><strong>Source</strong> - streams consume from a data-providing source such as collections, arrays, or I/O resources. Generating stream from an ordered collection preserves the ordering</li>
<li><strong>Data processing operations</strong> - streams support database like operations from functional programming languages such as filter, map, reduce, find, match, sort</li>
<li><strong>Pipelining</strong> - many stream return stream themselves, allowing operations to be chained and form a larger pipeline.Hence, certain optimizations can be achieved such as laziness and short-circuiting</li>
<li><strong>Internal iteration</strong> - in contrast to collections, which are iterated explicitly using iterator, stream operations do the iteration behind the scenes for you.</li>
</ul>
<p><strong>Collections vs Streams</strong> - difference between collections and streams are in the way things are computed. A collection is in-memory data structure that holds every element in collection has to be computed before it can be added to collection</p>
<ul>
    <li>In contrast stream is conceptually fixed data structure where elements are computed on demand (in management speak this is demand driven).</li>
    <li>Good example is with dvd and internet movie. In dvd all data is stored in disk, while in internet movie  value a computed only as they needed</li>
    <li>Stream can be traversed only once. From philosophical viewpoints in stream values are distributed in time while in collections values are distributed in space (memory).</li>
    <li>Also, in stream we have intermediate operations like filter, map, limit, sorted, distinct. Also, there are terminal operations such as forEach, collect, count </li>
</ul>
<p></p>