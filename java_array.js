const convert = (input) => {
	return input.replaceAll("[","{").replaceAll("]","}");
}

console.log(convert(
	`
[[0,0,3,0,0,0,0],[1,0,3,0,0,0,0],[0,0,3,0,0,0,0],[0,0,2,0,0,3,3],[2,2,2,0,2,0,0],[3,3,2,0,2,0,3],[3,3,2,0,2,0,4]]
`
));


