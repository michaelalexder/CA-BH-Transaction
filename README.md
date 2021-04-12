# CA-BH-Transaction-service

Service is designed to work with account transactions

## API
**POST /api/v1/transaction** - Create transaction for account

Request example:
```json
{ 
  "account": "2d2ddda5-9ad5-4917-a280-405d220868aa", 
  "amount": 0.3
}
```

**POST /api/v1/account/list** - Retrieve transaction for accounts

Request example:

```json
{
  "accountIds": [
    "2d2ddda5-9ad5-4917-a280-405d220868aa"
  ]
}
```

Response example:
```json
[
  { 
    "account": "2d2ddda5-9ad5-4917-a280-405d220868aa",
    "amount": 0.3,
    "createdAt": "2021-04-12T20:23:24.064Z",
    "updatedAt": "2021-04-12T20:23:24.064Z"
  } 
]
```
