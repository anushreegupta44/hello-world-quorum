pragma solidity ^0.4.17;

contract HelloWorld {
    string public greeting;

    constructor(string _greeting) public{
        greeting = _greeting;
    }
}