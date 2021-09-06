const convert = (input) => {
	return input.replaceAll("[","{").replaceAll("]","}");
}

console.log(convert(
`
[[3, 6], [4, 3], [3, 2], [1, 3], [1, 2], [2, 4], [5, 2]]
`
));


