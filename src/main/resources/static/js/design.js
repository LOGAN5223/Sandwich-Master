let ingredientCheckArr = document.querySelectorAll(".ingredient-check");
let ingredientArr = document.querySelectorAll(".ing-holder");

ingredientCheckArr[0].checked = true;

for(let i = 0; i < ingredientCheckArr.length - 3; i++){
      ingredientCheckArr[i].onclick = () =>{
            if(i == 0){
                  ingredientArr[ingredientArr.length-1].style.backgroundImage = "url('../images/baggueteInvW.png')";
                  ingredientArr[0].style.backgroundImage = "url('../images/baggueteW.png')";
            }
            else if(i == 1){
                  ingredientArr[ingredientArr.length-1].style.backgroundImage = "url('../images/toastInvW.png')";
                  ingredientArr[0].style.backgroundImage = "url('../images/toastW.png')";
            }
            else{
                  ingredientArr[i - 1].style.opacity = ingredientArr[i - 1].style.opacity == "" ? "1" : ingredientArr[i - 1].style.opacity == "1" ? "0" : "1";
            }
      }
}