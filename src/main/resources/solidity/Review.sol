pragma solidity >=0.4.25 <0.7.0;

contract Review{
    struct Review{
        uint project_id;
        uint status;
        string opinion;
        uint create_time;
        uint update_time;
    }

    Review[] public reviews;

    mapping(uint => uint) index;

    event Response(uint code,string message);

    function getReview(uint project_id) public returns(uint,uint,string memory,uint,uint){
        require(index[project_id]<reviews.length);
        return (reviews[index[project_id]].project_id,reviews[index[project_id]].status,reviews[index[project_id]].opinion,reviews[index[project_id]].create_time,reviews[index[project_id]].update_time);
    }

    function insertReview(uint project_id,uint status,string memory opinion) public{
        if(index[project_id]<reviews.length)
            emit Response(0,"Have been existed");
        Review memory review= Review(project_id,status,opinion,now,now);
        reviews.push(review);
        index[review.project_id]=reviews.length-1;
        emit Response(1,"Success");
    }

    function updateReview(uint project_id,uint status,string memory opinion) public{
        if(index[project_id]>=reviews.length)
            emit Response(0,"No such review");
        else{
            reviews[index[project_id]].status=status;
            reviews[index[project_id]].opinion=opinion;
            reviews[index[project_id]].update_time=now;
            emit Response(1,"Success");
        }
    }
}
