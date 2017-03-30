# Managed Transactions
The best practice is that you use Container Managed Transactions. Apart from that, if you still want to use BMT: don't mix them.

# Difference btw CMT & BMT
### Container Managed Transaction (CMT)
annotation:  @TransactionManagement(TransactionManagementType.CONTAINER) <br>
automatically begins and commits transactions based on declarative metadata provided by the bean developer (all the logic for synchronizing the bean’s state with the database is handled automatically by the container)
supports the following transactions attributes:<br>
<br> Required: TransactionAttributeType.REQUIRED (bean’s method invocation is made within a transactional context. If a client is not associated with a transaction, a new transaction is invoked automatically.)<br>
RequiresNew: TransactionAttributeType.REQUIRES_NEW (a method is invoked in a new transaction context.)
<br> <br> Mandatory: TransactionAttributeType.MANDATORY ( if a transactional context exists, a Container acts like the transaction attribute is Required, else it throws a javax.ejb.TransactionRequiredException.)
NotSupported: TransactionAttributeType.NOT_SUPPORTED (transaction context is unspecified)
<br> <br> Supports: TransactionAttributeType.SUPPORTS (if a transactional context exists, a Container acts like the transaction attribute is Required, else – like NotSupported.)
<br> <br> Never: TransactionAttributeType.NEVER (a method executes only if no transaction context is specified.)

### Bean Managed Transactions:
Uses the javax.transaction.UserTransaction methods begin and commit to demarcate transactions. (the bean-provider is responsible for ensuring that a transaction is started and committed when appropriate)
<br> <br> Only session and message-driven beans may use BMT
<br><br> A stateless session bean instance must commit any transactions that it started before a business method or timeout callback method returns.
