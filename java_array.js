const convert = (input) => {
	return input.replaceAll("[","{").replaceAll("]","}");
}

console.log(convert(
`
[[0,0,0],[1,1,0],[1,1,1]]
`
));

console.log(convert(
	`
[[1,1,1],[1,0,0],[0,0,0]]
`
));

