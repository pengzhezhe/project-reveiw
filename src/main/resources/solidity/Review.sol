pragma solidity >=0.4.25 <0.7.0;

contract Review{
    struct Project{
        uint id;
        uint status;
        string opinion;
        uint review_time;
    }


    mapping(uint => Project) projects;

    function getResult(uint project_id) view public returns(uint,uint,string memory,uint){
        return (projects[project_id].id,projects[project_id].status,projects[project_id].opinion,projects[project_id].review_time);
    }

    event Response(uint code,string message);

    function insert(uint project_id,uint status,string memory opinion) public{
        projects[project_id]=Project(project_id,status,opinion,now);
        emit Response(1,"Success");
    }

}
