# HelloWorld-IconBlockchain
 My first project on the IconBlockchain

## How to use
1. Clone the repo
2. Deploy the `hello_world_score` (e.g. to your local testnet)
3. Update the `keystore_test1` to match your credentials
4. Execute `TestMain` and don't forget to update the score address

## Example Output
```
= All Score functions =================================================================================
greet
hello
increment
my_value

= Increment Transaction ===============================================================================
Transaction Status: success
TransactionResult{properties=RpcObject(items={blockHash=0xc98265cb60354291df0fc75a15a3b7abc1ef7b93e810300fda9db9f11687ffb3, cumulativeStepUsed=0x20418, logsBloom=0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, eventLogs=RpcArray(items=[]), blockHeight=0x3d1e, stepPrice=0x2540be400, to=cx7227e0adfc4bd41ea16bf2521089798cf10f67ca, txHash=0xe1533ef37d79621158c050f48756adb197c6154eed8b683b078c9f93c5b57ae0, txIndex=0x0, stepUsed=0x20418, status=0x1})}

= Read my current value out ===========================================================================
1

Process finished with exit code 0
```

## What did I learn?
* How the blockchain works in general
    + What the IconBlockchain is
* How to install a local testnet for the IconBlockchain
* How to write and deploy scores
* How to make transaction and score calls