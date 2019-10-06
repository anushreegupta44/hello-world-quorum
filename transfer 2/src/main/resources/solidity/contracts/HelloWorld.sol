pragma solidity ^0.4.17;

contract HelloWorld {
    string public greeting;

    event UpdateGreeting(
     string greeting
    );

    constructor(string _greeting) public{
        greeting = _greeting;
    }

    function update (string _newGreeting) public {
        greeting = _newGreeting;
        emit UpdateGreeting(greeting);
    }
}