const grpc = require('grpc');
const protoloader = require('@grpc/proto-loader');

const packageDef = protoloader.loadSync('proto/bank-service.proto');
const protoDesc = grpc.loadPackageDefinition(packageDef);

const client = new protoDesc.BankService('localhost:6566',grpc.credentials.createInsecure());

client.getBalance({accountNumber:4},(err,balance)=>{
    if(err){
        console.error(err)
        return;
    }
    console.log(balance);    
})
