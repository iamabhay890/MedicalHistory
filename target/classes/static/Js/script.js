
 const search=()=>{
    console.log("Running search function")
 //previous running code
    let query=$("#search-input").val();

    if(query==''){
    $(".search-result").hide();
    }else{
        console.log(query);

//            sending request
        let url=`/search/${query}`;
        fetch(url)
        .then((response)=>{
        return response.json();
        })
        .then((data)=>{
        console.log(data);

        let text=`<div class='list-group'>`
        data.forEach((medicine)=>{

        text+=`<h6 onclick="takeInput('${medicine.name}')">${medicine.name}</h6>`

        });

        text+=`</div>`;
        $(".search-result").html(text);
        $(".search-result").show();
        });
    }


}

function takeInput(n){
            console.log(n);
            document.getElementById("search-input").value=n;
            $(".search-result").hide();

}




