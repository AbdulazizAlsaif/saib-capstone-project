# SAIB Capstone Project

>NOTE: Please don't use the providid jar file in the target folder because the application.properties is different than the one in the github repository</br>
>NOTE: Please modify application.properties to your local database/tokens</br>
>NOTE: Please read the following note in  [AddTrasaction](#addtrasaction) Section before adding transaction

## Transaction API
- [GetAllTransactions](#getalltransactions)
- [GetAllTransactionsWithPagination](#getalltransactionswithpagination)
- [GetAllTransactionsWithPaginationAndSorted](#getalltransactionswithpaginationandsorted)
- [AddTrasaction](#addtrasaction)
- [UpdateTrasaction](#updatetrasaction)
- [DeleteTrasaction](#deletetrasaction)
- [GetTrasactionByTransactionId](#gettrasactionsbytransactionid)
- [GetTrasactionsByAccountId](#gettrasactionsbyaccountid)
- [GetTrasactionsByDate](#gettrasactionsbydate)
- [GetTrasactionsByType](#gettrasactionsbytype)
- [GetTrasactionsByDate&Type](#gettrasactionsbydateandtype)

 

### 'getAllTransactions'

#### Request and Response

![get All Transactions](https://github.com/AbdulazizAlsaif/saib-capstone-project/blob/master/capstone/ScreenShots/getAllTransactions.png)

### 'GetAllTransactionsWithPagination'


#### Request

![get All Transactions](https://github.com/AbdulazizAlsaif/saib-capstone-project/blob/master/capstone/ScreenShots/getAllTransactionsPaginationReq.png)

#### Response

![get All Transactions](https://github.com/AbdulazizAlsaif/saib-capstone-project/blob/master/capstone/ScreenShots/getAllTransactionsPaginationRes.png)

### 'GetAllTransactionsWithPaginationAndSorted'

#### Request 

![get All Transactions](https://github.com/AbdulazizAlsaif/saib-capstone-project/blob/master/capstone/ScreenShots/getAllTransactionsPaginationAndSortedReq.png)

#### Response

![get All Transactions](https://github.com/AbdulazizAlsaif/saib-capstone-project/blob/master/capstone/ScreenShots/getAllTransactionsPaginationAndSortedRes.png)

### AddTrasaction

>NOTE: I have added some logic to adding a transaction so make sure you do it before testing.</br>
1-fromAccount and toAccount ids must exist in the account database or it will return account not found</br>
2-Amount should not be > balance in the fromAccount or it will return insufficient fund

#### Request

![get All Transactions](https://github.com/AbdulazizAlsaif/saib-capstone-project/blob/master/capstone/ScreenShots/addTransactionReq.png)

#### Response

![get All Transactions](https://github.com/AbdulazizAlsaif/saib-capstone-project/blob/master/capstone/ScreenShots/addTransactionRes.png)

### UpdateTrasaction 

#### Request

![get All Transactions](https://github.com/AbdulazizAlsaif/saib-capstone-project/blob/master/capstone/ScreenShots/updateTransactionReq.png)


#### Response

![get All Transactions](https://github.com/AbdulazizAlsaif/saib-capstone-project/blob/master/capstone/ScreenShots/updateTransactionRes.png)

### DeleteTrasaction

#### Request

![get All Transactions](https://github.com/AbdulazizAlsaif/saib-capstone-project/blob/master/capstone/ScreenShots/deleteTransactionReq.png)

#### Response

![get All Transactions](https://github.com/AbdulazizAlsaif/saib-capstone-project/blob/master/capstone/ScreenShots/deleteTransactionRes.png)

### getTrasactionsByTransactionId

#### Request

![get All Transactions](https://github.com/AbdulazizAlsaif/saib-capstone-project/blob/master/capstone/ScreenShots/getTransactionByTransactionIdReq.png)

#### Response

![get All Transactions](https://github.com/AbdulazizAlsaif/saib-capstone-project/blob/master/capstone/ScreenShots/getTransactionByTransactionIdRes.png)

### getTrasactionsByAccountId

#### Request

![get All Transactions](https://github.com/AbdulazizAlsaif/saib-capstone-project/blob/master/capstone/ScreenShots/getAllTransactionsByAccountIdReq.png)

#### Response

![get All Transactions](https://github.com/AbdulazizAlsaif/saib-capstone-project/blob/master/capstone/ScreenShots/getAllTransactionsByAccountIdRes.png)

### getTrasactionsByDate

#### Request

![get All Transactions](https://github.com/AbdulazizAlsaif/saib-capstone-project/blob/master/capstone/ScreenShots/getTransactionByDateReq.png)

#### Response


![get All Transactions](https://github.com/AbdulazizAlsaif/saib-capstone-project/blob/master/capstone/ScreenShots/getTransactionByDateRes.png)

### getTrasactionsByType


#### Request


![get All Transactions](https://github.com/AbdulazizAlsaif/saib-capstone-project/blob/master/capstone/ScreenShots/getTransactionByTypeReq.png)

#### Response

![get All Transactions](https://github.com/AbdulazizAlsaif/saib-capstone-project/blob/master/capstone/ScreenShots/getTransactionByTypeRes.png)


### getTrasactionsByDateAndType


#### Request

![get All Transactions](https://github.com/AbdulazizAlsaif/saib-capstone-project/blob/master/capstone/ScreenShots/getTransactionByDateAndTypeReq.png)


#### Response

![get All Transactions](https://github.com/AbdulazizAlsaif/saib-capstone-project/blob/master/capstone/ScreenShots/getTransactionByDateAndTypeRes.png)







