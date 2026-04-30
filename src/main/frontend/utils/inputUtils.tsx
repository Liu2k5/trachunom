export {
    correct as correct
};

const initialConsonants = [
    'ngh',
    'ch', 'gh', 'ng', 'kh', 'nh', 'ph', 'th', 'tr', 'gi', 'qu',
    'b', 'c', 'd', 'đ', 'g', 'h', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'x'
];
const confusingConsonants = ['gi', 'qu'];
const singleVowels: string[][] = [
    ["a", "á", "à", "ả", "ã", "ạ"],
    ["ă", "ắ", "ằ", "ẳ", "ẵ", "ặ"],
    ["â", "ấ", "ầ", "ẩ", "ẫ", "ậ"],
    ["e", "é", "è", "ẻ", "ẽ", "ẹ"],
    ["ê", "ế", "ề", "ể", "ễ", "ệ"],
    ["i", "í", "ì", "ỉ", "ĩ", "ị"],
    ["o", "ó", "ò", "ỏ", "õ", "ọ"],
    ["ô", "ố", "ồ", "ổ", "ỗ", "ộ"],
    ["ơ", "ớ", "ờ", "ở", "ỡ", "ợ"],
    ["u", "ú", "ù", "ủ", "ũ", "ụ"],
    ["ư", "ứ", "ừ", "ử", "ữ", "ự"],
    ["y", "ý", "ỳ", "ỷ", "ỹ", "ỵ"]
];
const diphthongsTonePos1: string[] = [
    'ai', 'ao', 'au', 'ay',
    'âu', 'ây',
    'eo',
    'êu',
    'ia', 'iu',
    'oi',
    'ôi',
    'ơi',
    'ua', 'ui',
    'ưa', 'ưi', 'ưu',
    'ya'
];
const diphthongsTonePos2: string[] = [
    'iê',
    'oa', 'oă', 'oe', 'oo',
    'ôô',
    'uă', 'uâ', 'ue', 'uê', 'uô', 'uơ', 'uy',
    'ươ',
    'yê'
];
const triphthongsTonePos2: string[] = [
    'oai', 'oao', 'uao', 'oeo', 'iêu', 'yêu', 'uôi', 'ươu', 'uyu', 'oay', 'uây', 'ươi'
];
const triphthongsTonePos3: string[] = [
    'uyê'
];
const finalConsonants = [
    'ch', 'ng', 'nh',
    'c', 'm', 'n', 'p', 't'
];
const wVowels = [
    'oa', 'oă', 'oe', 'uă', 'uâ', 'ue', 'uê', 'uơ', 'uy',
    'uyê', 'oai', 'oao', 'uao', 'oeo', 'uyu', 'oay', 'uây'
];

function correct(input: string) {
    // ignores strings containing cjk characters
    for (let i = 0; i < input.length; i++) {
        if ((input.codePointAt(i) ?? 0) >= 0x4e00) {
            return input;
        }
    }

    var splitStrings = input
        .replace('/[^\\w\\s]|_/g', '')
        .replace('f', 'ph')
        .replace('j', 'gi')
        .replace('w', 'u')
        .replace('z', 'd')
        .toLowerCase()
        .split(' ');
    var separatedWordsAndTones = splitStrings.map(o => separateToneDiacritics(o));
    var output: string[] = [];

    // console.log(splitStrings);
    for (let i = 0; i < separatedWordsAndTones.length; i++) {
        let word = separatedWordsAndTones.at(i)?.at(0) as string;
        let initialConsonant = getInitialConsonant(word);
        let vowel = getVowel(word);
        let finalConsonant = getFinalConsonant(word);
        let tone = separatedWordsAndTones.at(i)?.at(1) as number;

        console.log(splitStrings[i] +  ": " + initialConsonant + " " + vowel + " " + finalConsonant + " " + tone);

        // case 1: clear combinations
        if (!confusingConsonants.includes(initialConsonant)) {
            if (['kạn', 'đắk', 'lắk', 'ku'].includes(splitStrings[i])) {
                output.push(splitStrings[i]);
            } else {
                if (initialConsonant !== '' && vowel === 'y') {
                    vowel = 'i';
                } else if (vowel === 'ui' && finalConsonant !== '') {
                    vowel = 'uy';
                } else if (initialConsonant === 'c' && ['e', 'ê', 'i'].includes(vowel.charAt(0))) {
                    initialConsonant = 'k';
                } else if (initialConsonant === 'k' && !['e', 'ê', 'i'].includes(vowel.charAt(0))) {
                    initialConsonant = 'c';
                } else if (initialConsonant === 'g' && ['e', 'ê', 'i'].includes(vowel.charAt(0))) {
                    initialConsonant = 'gh';
                } else if (initialConsonant === 'gh' && !['e', 'ê', 'i'].includes(vowel.charAt(0))) {
                    initialConsonant = 'g';
                } else if (initialConsonant === 'ng' && ['e', 'ê', 'i'].includes(vowel.charAt(0))) {
                    initialConsonant = 'ngh';
                } else if (initialConsonant === 'ngh' && !['e', 'ê', 'i'].includes(vowel.charAt(0))) {
                    initialConsonant = 'ng';
                }
                if (['c', 'k'].includes(initialConsonant) &&
                    wVowels.includes(vowel)) {
                    initialConsonant = 'qu';
                    vowel = vowel.slice(1);
                }
                vowel = combineToneDiacritic(vowel, tone);
                output.push(initialConsonant + vowel + finalConsonant);
            }

        }
        // case 2: gi/qu combinations
        else {
            if (['gịa', 'gỵa', 'gìn', 'quốc'].includes(splitStrings[i])) {
                output.push(splitStrings[i])
            } else if (initialConsonant === 'gi') {
                vowel = combineToneDiacritic(
                    vowel.charAt(0) === 'i'
                        ? vowel.slice(1)
                        : vowel
                    , tone); // eliminate the redundant 'i'
                output.push(initialConsonant + vowel + finalConsonant);
            } else if (initialConsonant === 'qu') {
                if (vowel.charAt(0) === 'u') {
                    vowel = vowel.slice(1);
                }
                if (vowel.charAt(0) === 'i') {
                    vowel = 'y' + vowel.slice(1);
                }
                vowel = combineToneDiacritic(vowel, tone);
                output.push(initialConsonant + vowel + finalConsonant);
            }
        }

        // console.log(splitStrings[i] + ": " + output[output.length - 1]);
    }
    let outputString = '';
    for (let i = 0; i < output.length; i++) {
        outputString += output.at(i) + ' ';
    }
    return outputString.trim();
}

function separateToneDiacritics(input: string) {
    var word = '';
    var tone = 0;
    for (let i = 0; i < input.length; i++) {
        let found = false;
        let tempString = input.charAt(i);
        for (let j = 0; j < singleVowels.length; j++) {
            let index = singleVowels.at(j)?.indexOf(tempString);
            if (index && index > 0) { // tone 0 is already default
                tone = index as number;
                found = true;
                word += singleVowels.at(j)?.at(0);
                break;
            }
        }
        if (!found) {
            word += tempString;
        }
    }
    return [word, tone];
}

function combineToneDiacritic(vowel: string, tone: number) {
    switch (vowel.length) {
        case 1:
            return characterPlusTone(vowel, tone);
        case 2:
            if (diphthongsTonePos1.includes(vowel)) {
                return characterPlusTone(vowel.charAt(0), tone) + vowel.charAt(1);
            } else if (diphthongsTonePos2.includes(vowel)) {
                return vowel.charAt(0) + characterPlusTone(vowel.charAt(1), tone);
            }
            break;
        case 3:
            if (triphthongsTonePos2.includes(vowel)) {
                return vowel.charAt(0) + characterPlusTone(vowel.charAt(1), tone) + vowel.charAt(2);
            } else if (triphthongsTonePos3.includes(vowel)) {
                return vowel.charAt(0) + vowel.charAt(1) + characterPlusTone(vowel.charAt(2), tone);
            }
            break;
    }
    return '';
}

function characterPlusTone(word: string, tone: number) {
    for (let i = 0; i < singleVowels.length; i++) {
        if (word === singleVowels[i][0]) {
            // console.log(word + " " + tone + " -> " + singleVowels.at(index)?.at(tone) as string);
            return singleVowels.at(i)?.at(tone) as string;
        }
    }
    return '';
}

function getInitialConsonant(word: string) {
    for (let i = 0; i < initialConsonants.length; i++) {
        let tempString: string = initialConsonants.at(i) as string;
        if (word.indexOf(tempString) == 0) {
            return tempString;
        }
    }
    return '';
}

function getVowel(word: string) {
    let tempVowelArray: string[] = [
        ...triphthongsTonePos2,
        ...triphthongsTonePos3,
        ...diphthongsTonePos1,
        ...diphthongsTonePos2,
        ...singleVowels.map(o => o[0] as string)
    ];
    for (let i = 0; i < tempVowelArray.length; i++) {
        let tempString = tempVowelArray[i];
        if (word.includes(tempString)) {
            return tempString;
        }
    }
    return '';
}

function getFinalConsonant(word: string) {
    for (let i = 0; i < finalConsonants.length; i++) {
        let tempString = finalConsonants.at(i) as string;
        let index = word.lastIndexOf(tempString);
        if (index > 0) {
            return tempString;
        }
    }
    return '';
}