let ingHolderArr = document.querySelectorAll(".ingredient-holder");
let ingImgArr = ["big_cheese.png", "big_cucumber.png", "big_peperoni.png", "big_tomatoes.png"];

for(let i = 0; i < 4; i++){
      ingHolderArr[i].style.backgroundImage = "url('../images/" + ingImgArr[Math.floor(Math.random() * (4 - 1) + 1)] + "')";
}