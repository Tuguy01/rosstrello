const itemsFromBackend = [
    {id: 'one', content: 'First task'},
    {id: 'two', content: 'Second task'},
    {id: 'tree', content: 'Third task'},
    {id: 'four', content: "Fourth task"}
]

const columnsFromBackend =
    {
        [1]: {
            name: 'Requested',
            items: itemsFromBackend
        },
        [2]: {
            name: 'To do',
            items: []
        },
        [3]: {
            name: 'In progress',
            items: []
        },
        [4]: {
            name: 'Under review',
            items:[]
        }
    };