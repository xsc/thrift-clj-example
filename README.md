# thrift-clj-example

Example for [thrift-clj](https://github.com/xsc/thrift-clj) usage.

## Usage

The example implements a simple storage server that stores `Person` objects using a given ID
and delivers them when requested.

__Starting the Server__

``
lein run -m server [<Port>]
``

Default Port is 7007.

__Starting the Client__

``
lein run -m client [-h <Host>] [-p <Port>] -c [store  <ID> <First Name> <Last Name> <Age> | get <ID>]
``

## License

Copyright &copy; 2013 Yannick Scherer

Distributed under the Eclipse Public License, the same as Clojure.
