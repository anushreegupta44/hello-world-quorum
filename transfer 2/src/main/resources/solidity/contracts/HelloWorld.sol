pragma solidity ^0.4.17;

import "./ContractRegistry.sol";

contract HelloWorld {
    string public greeting;
    ContractRegistry private contractRegistry;
    event UpdateGreeting(
     string greeting
    );

    event AddGreeting(
     string greeting
    );

    constructor(address _contractRegistryAddress, string _greeting, string _sender, string _receiver) public{
        contractRegistry = ContractRegistry(_contractRegistryAddress);
        greeting = _greeting;
        contractRegistry.addContract(_sender, _receiver);
        emit AddGreeting(greeting);
    }

    function update (string _newGreeting) public {
        greeting = _newGreeting;
        emit UpdateGreeting(greeting);
    }
}