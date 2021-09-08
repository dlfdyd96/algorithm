const convert = (input) => {
	return input.replaceAll("[","{").replaceAll("]","}");
}

console.log(convert(
`
[[0, 3], [1, 9], [2, 6]]
`
));


