pragma solidity ^0.4.17;

contract ContractRegistry {
    mapping(string => mapping(string => address)) private contractRegistryMap;
    string public contractRegistryVersion;

    constructor() public{
        contractRegistryVersion = "1";
    }

    function addContract(string _sender, string _receiver) public {
        contractRegistryMap[_sender][_receiver] = msg.sender;
        contractRegistryMap[_receiver][_sender] = msg.sender;
    }

    function getContract(string _sender, string _receiver) public view returns (address) {
        return contractRegistryMap[_sender][_receiver];
    }
}