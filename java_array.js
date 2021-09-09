const convert = (input) => {
	return input.replaceAll("[","{").replaceAll("]","}");
}

console.log(convert(
`
[[7], [3, 8], [8, 1, 0], [2, 7, 4, 4], [4, 5, 2, 6, 5]]
`
));


