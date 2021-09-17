const convert = (input) => {
	return input.replaceAll("[","{").replaceAll("]","}");
}

console.log(convert(
	`
[[4, 3], [4, 2], [3, 2], [1, 2], [2, 5]]
`
));


