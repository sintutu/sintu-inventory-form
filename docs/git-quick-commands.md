Good. Just a clean list. No explanation. These are the ones that usually complete the mental model once you’re past fetch/merge/rebase/switch/reset:

* `git reset`
* `git restore`
* `git cherry-pick`
* `git revert`
* `git stash`
* `git reflog`
* `git log` (with graph options)
* `git show`
* `git diff`
* `git tag`
* `git branch --set-upstream-to`
* `git push --force-with-lease`
* `git clean`
* `git commit --amend`
* `git rebase -i`
* `git bisect`
* `git worktree`
* `git remote`
* `git config`
* `git describe`
* `git blame`

If you want structure, we can next group them by what they mutate:

* commit graph
* branch labels
* HEAD
* working directory
* remote state

That’s where Git becomes predictable instead of emotional.
